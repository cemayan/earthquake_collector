import { AsyncStorage } from 'react-native';

const retrieveUserData = async () => {
    try {
      const value = await AsyncStorage.getItem('userId');
      return value;
    } catch (error) {
    }
  };

  export default retrieveUserData;