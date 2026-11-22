(() => {
    const searchInput = document.querySelector("#item-search");
    const cards = [...document.querySelectorAll("[data-item-card]")];
    const chips = [...document.querySelectorAll("[data-category-filter]")];
    const emptyState = document.querySelector("#empty-state");
    const announcement = document.querySelector("#results-announcement");
    let activeCategory = "Todos";

    const normalize = (value) => value
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLocaleLowerCase("pt-BR");

    const applyFilters = () => {
        const query = normalize(searchInput.value.trim());
        let visibleCount = 0;

        cards.forEach((card) => {
            const titleMatches = normalize(card.dataset.title).includes(query);
            const placeMatches = normalize(card.dataset.place).includes(query);
            const categoryMatches = activeCategory === "Todos" || card.dataset.category === activeCategory;
            const visible = (query === "" || titleMatches || placeMatches) && categoryMatches;

            card.hidden = !visible;
            if (visible) visibleCount += 1;
        });

        emptyState.hidden = visibleCount !== 0;
        announcement.textContent = visibleCount === 1
            ? "1 item encontrado."
            : `${visibleCount} itens encontrados.`;
    };

    searchInput.addEventListener("input", applyFilters);

    chips.forEach((chip) => {
        chip.addEventListener("click", () => {
            activeCategory = chip.dataset.categoryFilter;
            chips.forEach((otherChip) => {
                const selected = otherChip === chip;
                otherChip.classList.toggle("is-active", selected);
                otherChip.setAttribute("aria-pressed", String(selected));
            });
            applyFilters();
        });
    });

    applyFilters();
})();
