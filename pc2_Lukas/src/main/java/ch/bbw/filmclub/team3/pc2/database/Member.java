package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String title;
    private String phone;
    private Timestamp entryDate;
    private Collection<BoardMember> boardMembersByMemberId;
    private Collection<Visit> visitsByMemberId;

    @Id
    @Column(name = "member_id")
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "entry_date", nullable = true)
    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberId != member.memberId) return false;
        if (firstName != null ? !firstName.equals(member.firstName) : member.firstName != null) return false;
        if (lastName != null ? !lastName.equals(member.lastName) : member.lastName != null) return false;
        if (address != null ? !address.equals(member.address) : member.address != null) return false;
        if (city != null ? !city.equals(member.city) : member.city != null) return false;
        if (title != null ? !title.equals(member.title) : member.title != null) return false;
        if (phone != null ? !phone.equals(member.phone) : member.phone != null) return false;
        if (entryDate != null ? !entryDate.equals(member.entryDate) : member.entryDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (entryDate != null ? entryDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "memberByMemberIdfs")
    public Collection<BoardMember> getBoardMembersByMemberId() {
        return boardMembersByMemberId;
    }

    public void setBoardMembersByMemberId(Collection<BoardMember> boardMembersByMemberId) {
        this.boardMembersByMemberId = boardMembersByMemberId;
    }

    @OneToMany(mappedBy = "memberByMemberIdfs")
    public Collection<Visit> getVisitsByMemberId() {
        return visitsByMemberId;
    }

    public void setVisitsByMemberId(Collection<Visit> visitsByMemberId) {
        this.visitsByMemberId = visitsByMemberId;
    }
}
