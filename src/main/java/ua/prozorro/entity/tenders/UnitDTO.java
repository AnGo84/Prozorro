package ua.prozorro.entity.tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Units")
public class UnitDTO {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private String code;

    @Column(length = 4000)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UnitDTO unitDTO = (UnitDTO) o;

        if (code != null ? !code.equals(unitDTO.code) : unitDTO.code != null)
            return false;
        return name != null ? name.equals(unitDTO.name) : unitDTO.name == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UnitDTO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
