import { AsyncStorage } from 'react-native';

const retrieveToken = async () => {
    try {
      const value = await AsyncStorage.getItem('token');
      return value;
    } catch (error) {
    }
  };

  export default retrieveToken;