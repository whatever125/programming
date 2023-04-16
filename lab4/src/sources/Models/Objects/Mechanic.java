package sources.Models.Objects;

public class Mechanic extends Malysh {
    private Malysh assistant;

    public Mechanic(String name) {
        super(name);
    }

    public Malysh getAssistant() {
        return assistant;
    }

    public void setAssistant(Malysh assistant) {
        this.assistant = assistant;
        System.out.println(getName() + " получил себе в помощники " + assistant.getName());
    }
}
