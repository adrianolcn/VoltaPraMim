package br.ucsal.voltapramim.web.home;

import java.util.List;

public final class ItemRepository {
    private final List<Item> items = List.of(
            new Item("Estojo preto", "Sala de aula", "Acessórios", "VPM-392MH", "aguardando", "hoje"),
            new Item("Garrafa térmica verde", "Biblioteca", "Outros", "VPM-QM9QS", "aguardando", "ontem"),
            new Item("Chaveiro azul com duas chaves", "Cantina", "Chaves", "VPM-W63S9", "aguardando", "há 2 dias"),
            new Item("Fone de ouvido branco", "Laboratório 3", "Eletrônicos", "VPM-K81TA", "devolvido", "há 3 dias")
    );

    public List<Item> findAll() {
        return items;
    }
}
