package br.ucsal.voltapramim.web.home;

public final class Item {
    private final String title;
    private final String place;
    private final String category;
    private final String ref;
    private final String status;
    private final String when;

    public Item(String title, String place, String category, String ref, String status, String when) {
        this.title = title;
        this.place = place;
        this.category = category;
        this.ref = ref;
        this.status = status;
        this.when = when;
    }

    public String getTitle() { return title; }
    public String getPlace() { return place; }
    public String getCategory() { return category; }
    public String getRef() { return ref; }
    public String getStatus() { return status; }
    public String getWhen() { return when; }
}
