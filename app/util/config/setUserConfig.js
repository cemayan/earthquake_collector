import { AsyncStorage } from 'react-native';

const storeUserConfig = async (userConfig) => {
    try {
      await AsyncStorage.removeItem('userConfig');
      await AsyncStorage.setItem('userConfig', userConfig);
    } catch (error) {
    }
   };


   export default storeUserConfig;