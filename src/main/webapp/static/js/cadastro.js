(() => {
    const input = document.querySelector('#foto');
    const drop = document.querySelector('#photo-drop');
    const selected = document.querySelector('#foto-selecionada');
    const preview = document.querySelector('#foto-preview');
    const removeButton = document.querySelector('#foto-remover');
    let previewUrl;
    const showSelection = () => {
        const file = input.files[0];
        selected.textContent = file ? file.name : '';
        removeButton.hidden = !file;
        if (previewUrl) URL.revokeObjectURL(previewUrl);
        previewUrl = undefined;
        preview.hidden = true;
        preview.removeAttribute('src');
        if (file && ['image/jpeg', 'image/png'].includes(file.type)) {
            previewUrl = URL.createObjectURL(file);
            preview.src = previewUrl;
            preview.hidden = false;
        }
    };
    preview.addEventListener('error', () => { preview.hidden = true; });
    input.addEventListener('change', showSelection);
    removeButton.addEventListener('click', () => {
        input.value = '';
        showSelection();
        input.focus();
    });
    ['dragenter', 'dragover'].forEach((eventName) => drop.addEventListener(eventName, (event) => {
        event.preventDefault();
        drop.classList.add('is-dragging');
    }));
    ['dragleave', 'drop'].forEach((eventName) => drop.addEventListener(eventName, (event) => {
        event.preventDefault();
        drop.classList.remove('is-dragging');
    }));
    drop.addEventListener('drop', (event) => {
        if (event.dataTransfer.files.length) {
            input.files = event.dataTransfer.files;
            showSelection();
        }
    });
})();
