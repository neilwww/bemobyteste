import java.util.Objects;

public class Users {
    private String telephone;
    private String status;
    private Country country;

    public Users () {

    }

    public Users(String telephone, String status) {
        this.telephone = telephone;
        this.status = status;
    }

    public Users(String telephone, String status, Country country) {
        this.telephone = telephone;
        this.status = status;
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(telephone, users.telephone) && Objects.equals(status, users.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone, status);
    }

    @Override
    public String toString() {
        return "Users{" +
                "telephone='" + telephone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
