package project.predictor.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "login_history")
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "uname")
    private String userName;
    private long login_epoch;
    private long logout_epoch;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "access_date")
    private Date accessDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getLogin_epoch() {
        return login_epoch;
    }

    public void setLogin_epoch(long login_epoch) {
        this.login_epoch = login_epoch;
    }

    public long getLogout_epoch() {
        return logout_epoch;
    }

    public void setLogout_epoch(long logout_epoch) {
        this.logout_epoch = logout_epoch;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }
}
