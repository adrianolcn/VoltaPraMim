(() => {
    const selects = [...document.querySelectorAll('.registration-form select[required]')];

    selects.forEach((select) => {
        const label = document.querySelector(`label[for="${CSS.escape(select.id)}"]`);
        if (!label) return;
        if (!label.id) label.id = `${select.id}-label`;

        const control = document.createElement('div');
        control.className = 'select-control';
        select.before(control);
        control.append(select);

        const trigger = document.createElement('button');
        trigger.type = 'button';
        trigger.className = 'select-trigger';
        trigger.id = `${select.id}-trigger`;
        trigger.setAttribute('role', 'combobox');
        trigger.setAttribute('aria-haspopup', 'listbox');
        trigger.setAttribute('aria-expanded', 'false');
        trigger.setAttribute('aria-labelledby', label.id);

        const menu = document.createElement('div');
        menu.className = 'select-menu';
        menu.id = `${select.id}-options`;
        menu.setAttribute('role', 'listbox');
        menu.setAttribute('aria-labelledby', label.id);
        menu.hidden = true;

        const options = [...select.options].map((option, index) => {
            const row = document.createElement('div');
            row.className = 'select-option';
            row.id = `${select.id}-option-${index}`;
            row.setAttribute('role', 'option');
            row.setAttribute('aria-selected', String(option.selected));
            row.textContent = option.textContent;
            row.addEventListener('pointerenter', () => setActive(index));
            row.addEventListener('click', () => choose(index));
            menu.append(row);
            return row;
        });

        trigger.setAttribute('aria-controls', menu.id);
        select.classList.add('select-native-hidden');
        select.tabIndex = -1;
        select.setAttribute('aria-hidden', 'true');
        select.removeAttribute('required');
        control.append(trigger, menu);

        let activeIndex = Math.max(select.selectedIndex, 0);

        function paintSelection() {
            const selectedIndex = Math.max(select.selectedIndex, 0);
            trigger.textContent = select.options[selectedIndex].textContent;
            trigger.classList.toggle('is-placeholder', !select.value);
            options.forEach((option, index) => {
                option.setAttribute('aria-selected', String(index === selectedIndex));
            });
        }

        function setActive(index) {
            activeIndex = Math.max(0, Math.min(index, options.length - 1));
            options.forEach((option, i) => option.classList.toggle('is-active', i === activeIndex));
            trigger.setAttribute('aria-activedescendant', options[activeIndex].id);
            options[activeIndex].scrollIntoView({block: 'nearest'});
        }

        function open() {
            menu.hidden = false;
            control.classList.add('is-open');
            trigger.setAttribute('aria-expanded', 'true');
            setActive(Math.max(select.selectedIndex, 0));
        }

        function close() {
            menu.hidden = true;
            control.classList.remove('is-open');
            trigger.setAttribute('aria-expanded', 'false');
            trigger.removeAttribute('aria-activedescendant');
            options.forEach((option) => option.classList.remove('is-active'));
        }

        function choose(index) {
            if (!select.options[index].disabled) {
                select.selectedIndex = index;
                paintSelection();
                trigger.removeAttribute('aria-invalid');
                trigger.removeAttribute('aria-describedby');
                control.querySelector('.select-error')?.remove();
                select.dispatchEvent(new Event('change', {bubbles: true}));
            }
            close();
            trigger.focus();
        }

        trigger.addEventListener('click', () => {
            if (menu.hidden) open(); else close();
        });

        trigger.addEventListener('keydown', (event) => {
            if (event.key === 'ArrowDown' || event.key === 'ArrowUp') {
                event.preventDefault();
                if (menu.hidden) open();
                else setActive(activeIndex + (event.key === 'ArrowDown' ? 1 : -1));
            } else if (event.key === 'Home' && !menu.hidden) {
                event.preventDefault();
                setActive(0);
            } else if (event.key === 'End' && !menu.hidden) {
                event.preventDefault();
                setActive(options.length - 1);
            } else if ((event.key === 'Enter' || event.key === ' ') && !menu.hidden) {
                event.preventDefault();
                choose(activeIndex);
            } else if (event.key === 'Escape' && !menu.hidden) {
                event.preventDefault();
                close();
            }
        });

        select.form?.addEventListener('submit', (event) => {
            if (!select.value) {
                event.preventDefault();
                trigger.setAttribute('aria-invalid', 'true');
                let error = control.querySelector('.select-error');
                if (!error) {
                    error = document.createElement('p');
                    error.className = 'select-error';
                    error.id = `${select.id}-error`;
                    error.setAttribute('role', 'alert');
                    error.textContent = 'Selecione uma opção.';
                    control.append(error);
                }
                trigger.setAttribute('aria-describedby', error.id);
                trigger.focus();
            }
        });

        document.addEventListener('pointerdown', (event) => {
            if (!control.contains(event.target)) close();
        });

        paintSelection();
        label.htmlFor = trigger.id;
    });
})();
