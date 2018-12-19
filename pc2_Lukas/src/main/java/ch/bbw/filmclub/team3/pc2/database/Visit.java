package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;

@Entity
@IdClass(VisitPK.class)
public class Visit {
    private int presentationIdfs;
    private int memberIdfs;
    private Presentation presentationByPresentationIdfs;
    private Member memberByMemberIdfs;

    @Id
    @Column(name = "presentation_idfs")
    public int getPresentationIdfs() {
        return presentationIdfs;
    }

    public void setPresentationIdfs(int presentationIdfs) {
        this.presentationIdfs = presentationIdfs;
    }

    @Id
    @Column(name = "member_idfs")
    public int getMemberIdfs() {
        return memberIdfs;
    }

    public void setMemberIdfs(int memberIdfs) {
        this.memberIdfs = memberIdfs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visit visit = (Visit) o;

        if (presentationIdfs != visit.presentationIdfs) return false;
        if (memberIdfs != visit.memberIdfs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentationIdfs;
        result = 31 * result + memberIdfs;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "presentation_idfs", referencedColumnName = "presentation_id", nullable = false, insertable = false, updatable = false)
    public Presentation getPresentationByPresentationIdfs() {
        return presentationByPresentationIdfs;
    }

    public void setPresentationByPresentationIdfs(Presentation presentationByPresentationIdfs) {
        this.presentationByPresentationIdfs = presentationByPresentationIdfs;
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
