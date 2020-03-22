import { AsyncStorage } from 'react-native';

const storeTokenData = async (token) => {
    try {
      await AsyncStorage.setItem('token', token);
    } catch (error) {
    }
   };


   export default storeTokenData;