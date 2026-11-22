package br.ucsal.voltapramim.web.home;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemRepositoryTest {
    @Test
    void forneceOsQuatroItensDeDemonstracaoEspecificados() {
        var items = new ItemRepository().findAll();

        assertEquals(4, items.size());
        assertItem(items.get(0), "Estojo preto", "Sala de aula", "Acessórios", "VPM-392MH", "aguardando", "hoje");
        assertItem(items.get(1), "Garrafa térmica verde", "Biblioteca", "Outros", "VPM-QM9QS", "aguardando", "ontem");
        assertItem(items.get(2), "Chaveiro azul com duas chaves", "Cantina", "Chaves", "VPM-W63S9", "aguardando", "há 2 dias");
        assertItem(items.get(3), "Fone de ouvido branco", "Laboratório 3", "Eletrônicos", "VPM-K81TA", "devolvido", "há 3 dias");
    }

    private void assertItem(Item item, String title, String place, String category, String ref, String status, String when) {
        assertEquals(title, item.getTitle());
        assertEquals(place, item.getPlace());
        assertEquals(category, item.getCategory());
        assertEquals(ref, item.getRef());
        assertEquals(status, item.getStatus());
        assertEquals(when, item.getWhen());
    }
}
