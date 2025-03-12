package com.example.projectsbcxp.Projects.api;
import com.example.projectsbcxp.Projects.api.to.PersonInProjectTO;
import com.example.projectsbcxp.Projects.api.to.PersonTO;
import com.example.projectsbcxp.Projects.impl.data.entities.PersonEntity;
import com.example.projectsbcxp.Projects.impl.data.entities.PersonProjectPositionEntity;

import java.util.List;

public interface PersonInterface {
    List<PersonTO> getActivePersons();
    List<PersonInProjectTO> getActivePositionsInProject();
    PersonInProjectTO getPositionById(int id);
    PersonInProjectTO updatePosition (PersonInProjectTO personInProjectTO);
}
