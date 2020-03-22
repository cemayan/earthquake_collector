import { action, observable, computed } from 'mobx';

class EarthQuakeStore {
  @observable name = 'Cem';

  @computed get getName() {
    return this.name;
  }

  @action setName(name) {
    this.name = name;
  }

}

const earthQuakeStore = new EarthQuakeStore();
export default earthQuakeStore;