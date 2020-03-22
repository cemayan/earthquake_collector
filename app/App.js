import * as React from 'react';
import { Platform, StatusBar, StyleSheet, View, AsyncStorage } from 'react-native';
import { SplashScreen } from 'expo';
import * as Font from 'expo-font';
import { Ionicons } from '@expo/vector-icons';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import {Provider} from 'mobx-react'
import earthQuakeStore from './store/EarthQuakeStore'
import {Root, Container} from 'native-base'
import registerForPushNotificationsAsync from './push/registerPushNotification'
import { Notifications } from 'expo';
import { Text } from 'react-native';
var Chance = require('chance');
import  getUserData  from './util/user/getUserData';
import  setUserData  from './util/user/setUserData';
import  getToken  from './util/token/getToken';

import BottomTabNavigator from './navigation/BottomTabNavigator';


const Stack = createStackNavigator();
var chance = new Chance();

export default class App extends React.Component {

  constructor(){
    super();
    this.state={
      isReady: false,
      notification: {}
    }

  }

  _handleNotification = notification => {
    this.setState({ notification: notification });
  };

  async componentDidMount() {

     var userId = await getUserData();
     if (userId === null) {
      await setUserData(chance.cf())
     } 
 
     var token =  await getToken();
     if (token === null) {
      registerForPushNotificationsAsync();
     }

    this._notificationSubscription = Notifications.addListener(this._handleNotification);
  }
  
  componentWillUnmount() {
    this.setState({notification:{}})
  }

  async UNSAFE_componentWillMount() {
    try {
      // Load fonts
      await Font.loadAsync({
        ...Ionicons.font,
        'space-mono': require('./assets/fonts/SpaceMono-Regular.ttf'),
        'Roboto': require('native-base/Fonts/Roboto.ttf'),
        'Roboto_medium': require('native-base/Fonts/Roboto_medium.ttf')
      });

      this.setState({isReady: true});

    } catch (e) {
      console.warn(e);
    } finally {

    }
  }

  render() {

    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
    <Text>Origin: {this.state.notification.origin}</Text>
    <Text>Data: {JSON.stringify(this.state.notification.data)}</Text>
    </View>
 
    if (!this.state.isReady)  {
      return null;
    } else {
      return (
        <Provider earthQuakeStore={earthQuakeStore} >
        <Root>
          <Container>
              {Platform.OS === 'ios' && <StatusBar barStyle="default" />}
              <NavigationContainer >
                <Stack.Navigator>
                      <Stack.Screen name="Earthquake Listener" component={BottomTabNavigator} />
                </Stack.Navigator>
              </NavigationContainer>
          </Container>
         </Root>
        </Provider>
      );
    }
    }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
});
