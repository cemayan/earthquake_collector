import * as React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import TabBarIcon from '../components/TabBarIcon';
import HomeScreen from '../screens/HomeScreen';
import LinksScreen from '../screens/LinksScreen';
import {Icon} from 'native-base'


const BottomTab = createBottomTabNavigator();
const INITIAL_ROUTE_NAME = 'Home';

export default function BottomTabNavigator({ navigation, route }) {
  return (
    <BottomTab.Navigator initialRouteName={INITIAL_ROUTE_NAME}  >
      <BottomTab.Screen
        name="Home"
        component={HomeScreen}
        options={{
          title: 'Home',
          tabBarIcon: ({ focused }) => <Icon type="Octicons" name="home" />,
        }}
      />
      <BottomTab.Screen
        name="About"
        component={LinksScreen}
        options={{
          title: 'About',
          tabBarIcon: ({ focused }) => <Icon type="Octicons"  name="info" />,
        }}
      />
    </BottomTab.Navigator>
  );
}
