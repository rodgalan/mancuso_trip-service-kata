package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

  private final TripDAO tripDAO;

  public TripService(TripDAO tripDAO) {
    this.tripDAO = tripDAO;
  }

  public List<Trip> getTripsByUser(User loggedUser, User requestedUser) throws UserNotLoggedInException {
    validateLoggedUser(loggedUser);
    return requestedUser.isFriendOf(loggedUser) ? getUserTrips(requestedUser) : new ArrayList<Trip>();
  }

  private void validateLoggedUser(User user) {
    if (user == null) {
      throw new UserNotLoggedInException();
    }
  }

  protected List<Trip> getUserTrips(User user) {
    return tripDAO.findTrips(user);
  }

}