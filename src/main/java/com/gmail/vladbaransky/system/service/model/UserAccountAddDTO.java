package com.gmail.vladbaransky.system.service.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/*@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode*/
public class UserAccountAddDTO implements Serializable {

    private Long id;

    @Pattern(regexp ="^[a-zA-Z!@]*$", message = "gabela1")
    @Size(min = 3, max = 16)
    private String username;

    @Pattern(regexp ="^[a-zA-Z0-9!@$#.]*$", message = "gabela2")
    @Size(min = 3, max = 16)
    private String password;

    @Pattern(regexp ="^[a-zA-Z0-9!@]*$", message = "gabela3")
    @Size(min = 1, max = 16)
    private String firstName;

    @Pattern(regexp ="^[a-zA-Z0-9!@]*$", message = "gabela4")
    @Size(min = 1, max = 16)
    private String lastName;
    private RoleEnum role;
    private StatusEnum status;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountAddDTO that = (UserAccountAddDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                role == that.role &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, role, status, createdAt);
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
