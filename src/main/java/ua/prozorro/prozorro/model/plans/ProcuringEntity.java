
package ua.prozorro.prozorro.model.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ua.prozorro.prozorro.model.tenders.Identifier;

public class ProcuringEntity {

    @SerializedName("identifier")
    @Expose
    private Identifier identifier;

    @SerializedName("name")
    @Expose
    private String name;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcuringEntity that = (ProcuringEntity) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProcuringEntity{");
        sb.append("identifier=").append(identifier);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
