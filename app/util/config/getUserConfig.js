import { AsyncStorage } from 'react-native';

const retrieveUserConfig= async () => {
    try {
      const value = await AsyncStorage.getItem('userConfig');
      return value;
    } catch (error) {
    }
  };

  export default retrieveUserConfig;