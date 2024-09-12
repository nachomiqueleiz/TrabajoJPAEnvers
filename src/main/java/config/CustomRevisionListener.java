package config;

import audit.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
            Revision revision = (Revision) revisionEntity;

    }
}