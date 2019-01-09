package ch.bbw.pc2.model.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class VisitPK implements Serializable {
    private int presentationIdfs;
    private int memberIdfs;

    @Column(name = "presentation_idfs")
    @Id
    public int getPresentationIdfs() {
        return presentationIdfs;
    }

    public void setPresentationIdfs(int presentationIdfs) {
        this.presentationIdfs = presentationIdfs;
    }

    @Column(name = "member_idfs")
    @Id
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

        VisitPK visitPK = (VisitPK) o;

        if (presentationIdfs != visitPK.presentationIdfs) return false;
        if (memberIdfs != visitPK.memberIdfs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentationIdfs;
        result = 31 * result + memberIdfs;
        return result;
    }
}
