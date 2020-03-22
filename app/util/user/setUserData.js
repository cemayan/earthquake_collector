import { AsyncStorage } from 'react-native';

const storeData = async (userId) => {
    try {
      await AsyncStorage.setItem('userId', userId);
    } catch (error) {
    }
   };


   export default storeData;