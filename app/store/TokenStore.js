import { action, observable, computed } from 'mobx';

class TokenStore {
  @observable token = '';

  @computed get getToken() {
    return this.token;
  }

  @action setToken(token) {
    this.token = token;
  }

}

const tokenStore = new TokenStore();
export default tokenStore;