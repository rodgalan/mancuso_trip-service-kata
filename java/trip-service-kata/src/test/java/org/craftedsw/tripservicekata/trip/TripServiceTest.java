package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

  @Test(expected = UserNotLoggedInException.class)
  public void when_user_not_logged_then_fails(){
    TripServiceTestable tripService = new TripServiceTestable();
    tripService.getTripsByUser(null);
  }



  class TripServiceTestable extends TripService{
    @Override
    protected User getLoggedUser() {
      return null;
    }
  }
	
}
