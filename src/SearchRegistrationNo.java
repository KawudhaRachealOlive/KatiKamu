public class SearchRegistrationNo implements Comparable<SearchRegistrationNo> {
    protected String name;

    public SearchRegistrationNo(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public int compareTo(SearchRegistrationNo another) {
        return this.name.compareTo(another.getName());
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof SearchRegistrationNo) {
            SearchRegistrationNo another = (SearchRegistrationNo) obj;
            if (this.name.equals(another.getName())) {
                return true;
            }
        }

        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

}