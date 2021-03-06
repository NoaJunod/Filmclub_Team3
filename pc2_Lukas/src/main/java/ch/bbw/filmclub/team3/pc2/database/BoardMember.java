package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;

@Entity
@Table(name = "board_member", schema = "filmclub_project", catalog = "")
public class BoardMember {
    private Integer compensation;
    private String function;
    private int memberIdfs;
    private int boardMemberId;
    private Member memberByMemberIdfs;

    @Basic
    @Column(name = "compensation")
    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }

    @Basic
    @Column(name = "function")
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Basic
    @Column(name = "member_idfs")
    public int getMemberIdfs() {
        return memberIdfs;
    }

    public void setMemberIdfs(int memberIdfs) {
        this.memberIdfs = memberIdfs;
    }

    @Id
    @Column(name = "board_member_id")
    public int getBoardMemberId() {
        return boardMemberId;
    }

    public void setBoardMemberId(int boardMemberId) {
        this.boardMemberId = boardMemberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardMember that = (BoardMember) o;

        if (memberIdfs != that.memberIdfs) return false;
        if (boardMemberId != that.boardMemberId) return false;
        if (compensation != null ? !compensation.equals(that.compensation) : that.compensation != null) return false;
        if (function != null ? !function.equals(that.function) : that.function != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = compensation != null ? compensation.hashCode() : 0;
        result = 31 * result + (function != null ? function.hashCode() : 0);
        result = 31 * result + memberIdfs;
        result = 31 * result + boardMemberId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "member_idfs", referencedColumnName = "member_id", nullable = false, insertable = false, updatable = false)
    public Member getMemberByMemberIdfs() {
        return memberByMemberIdfs;
    }

    public void setMemberByMemberIdfs(Member memberByMemberIdfs) {
        this.memberByMemberIdfs = memberByMemberIdfs;
    }
}
