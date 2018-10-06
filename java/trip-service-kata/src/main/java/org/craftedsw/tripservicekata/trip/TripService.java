package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

  public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
    User loggedUser = getLoggedUser();
    validateLoggedUser(loggedUser);
    boolean isFriend = user.getFriends().stream().anyMatch(friend -> friend.equals(loggedUser));
    return isFriend ? getUserTrips(user) : new ArrayList<Trip>();
  }

  protected User getLoggedUser() {
    return UserSession.getInstance().getLoggedUser();
  }

  private void validateLoggedUser(User user) {
    if (user == null) {
      throw new UserNotLoggedInException();
    }
  }

  protected List<Trip> getUserTrips(User user) {
    List<Trip> tripList;
    tripList = TripDAO.findTripsByUser(user);
    return tripList;
  }

}
