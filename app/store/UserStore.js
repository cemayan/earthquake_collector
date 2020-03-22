import { action, observable, computed } from 'mobx';

class UserStore {
  @observable userId = '';
  @observable location = '';
  @observable timeInterval = '';

  @computed get getUserId() {
    return this.userId;
  }

  @action setUserId(userId) {
    this.userId = userId;
  }

  @computed get getLocation() {
    return this.location;
  }

  @action setLocation(location) {
    this.location = location;
  }

  @computed get getTimeInterval() {
    return this.timeInterval;
  }

  @action setTimeInterval(timeInterval) {
    this.timeInterval = timeInterval;
  }

}

const userStore = new UserStore();
export default userStore;