import { Notifications } from 'expo';
import * as Permissions from 'expo-permissions';
import userStore from '../store/UserStore';
import  getUserData  from '../util/user/getUserData';
import  setToken  from '../util/token/setToken';
import  getToken  from '../util/token/getToken';

const PUSH_ENDPOINT = 'http://b65ada9b.ngrok.io/token-service/';


export default async function registerForPushNotificationsAsync() {
  const { status } = await Permissions.askAsync(Permissions.NOTIFICATIONS);

  if (status !== 'granted') {
    alert('No notification permissions!');
    return;
  }

  let getToken =  Notifications.getExpoPushTokenAsync().catch(err => {
    console.log(err);
  });

  let token = await getToken;
  await setToken(token);

  return fetch(PUSH_ENDPOINT, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      json: JSON.stringify({"token": token, "userId": await getUserData()}),
      token: token, 
      userId: await getUserData()
    }),
  }).catch(err=> {
    console.log(err);
  });
}