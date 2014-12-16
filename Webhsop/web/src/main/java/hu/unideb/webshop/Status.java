package hu.unideb.webshop;

public enum Status {

    FREE("FREE"), TRANSPORT("TRANSPORT"), READY("READY"), READYFORCREATE(
            "READYFORCREATE"), NEW("NEW"), DONE("DONE"), INPROGRESS(
                    "INPROGRESS"), NEEDMATERIAL("NEEDMATERIAL");
    private final String key;

    private Status(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Status getByKey(String key) {
        for (Status session : values()) {
            if (session.getKey().equals(key)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return LocaleSwitcher.getMessage("status_" + this.key);
    }
}
