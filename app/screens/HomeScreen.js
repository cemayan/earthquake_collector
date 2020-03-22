import * as React from 'react';
import { View, StyleSheet, TouchableOpacity,TouchableHighlight } from 'react-native';
import {Text, Button, Content, Input, Item, Toast, Root, Card, CardItem, Body, Label, Icon, Header, Right} from 'native-base'
import { observer, inject } from "mobx-react";
import Slider from "react-native-slider";
import  getUserData   from '../util/user/getUserData';
import  getUserConfig   from '../util/config/getUserConfig';
import  setUserConfig   from '../util/config/setUserConfig';
import userConfigService from '../service/userConfigService'
import { SwipeListView } from 'react-native-swipe-list-view';


@inject("earthQuakeStore")
@observer
export default class HomeScreen extends React.Component {

  _isMounted = false;

  constructor() {
    super();
    this.state={
        location:'',
        timeInterval: 1,
        textStatus: 'Save',
        userConfigs: [],
        editId: 0
    }

    this.handleHelpPress = this.handleHelpPress.bind(this);
    this.handleChangeText = this.handleChangeText.bind(this);
    this.handleSubmitEditing = this.handleSubmitEditing.bind(this);
    this.saveUserConfig = this.saveUserConfig.bind(this);
    this.handleEditButton = this.handleEditButton.bind(this);
    this.handleBackToSaveButton = this.handleBackToSaveButton.bind(this);
    this.deleteFromDb = this.deleteFromDb.bind(this);
  }

async componentDidMount() {
    this._isMounted = true;
    this.syncData();
 }

 syncData() {
    this.getUserConfigFromDb().then(async (result) => {
      this.setState({userConfigs:result})
      await setUserConfig(JSON.stringify({userConfigs:result}))
    }).catch(async () => {
       var confByStore = await this.getUserConfigFromStore();
       this.setState({userConfigs:JSON.parse(confByStore)});
    });
  }

  handleHelpPress() {
    console.log(this.props.earthQuakeStore.getName)
  } 

  async saveUserConfig(e) {

    var self = this;

    var userId = await getUserData();
    var configs = this.state.userConfigs;
    const found =  configs.find(x => x.location === this.state.location)

    if(typeof found === "undefined") {
      userConfigService.saveUserConfig({userId: userId, location: this.state.location, timeInterval: this.state.timeInterval,id: this.state.editId  })
      .then(async () => {

        if(this.state.editId != 0) {
          configs = configs.map(x=> {
              if(x.id == this.state.editId) {
                x.location = self.state.location
              }
              return x;
            });
        }
        else {
          configs.push({userId: userId,location: this.state.location, timeInterval: this.state.timeInterval })
        }

        this.setState({location: '', timeInterval:  1, textStatus:'Save', userConfigs: configs, editId: 0});
        await setUserConfig(JSON.stringify({userConfigs: configs}));
        Toast.show({
          text: 'New Configs saved succesfully!',
          duration: 1000
    
        });
      });

    }else {
      Toast.show({
        text: 'Same location found!',
        duration: 1000
  
      });
    }
  }

  async getUserConfigFromDb() {
    var userId = await getUserData();
    var userConfigArr = await userConfigService.getUserConfig(userId);
    return  userConfigArr;
  }

  async getUserConfigFromStore() {
    var userConfig = await getUserConfig();
    return  userConfig;
  }

  async deleteFromDb(id) {
    var result = await userConfigService.deleteFromDb(id);
  }


  componentWillUnmount() {
    this._isMounted = false;
    //this.setState({location:'', timeInterval: '' });
  }

  handleEditButton(e) {
      this.setState({location: e.location, timeInterval: e.timeInterval, textStatus:'Update', editId: e.id})
  }

  handleBackToSaveButton() {
    this.setState({location: '', timeInterval: 1,textStatus:'Save', editId:0})
  }

  async handleChangeText(location) {
    this.setState({location:location})
  } 

  handleSubmitEditing(e) {
    if(this.state.location !== "") {
      this.props.earthQuakeStore.setName(this.state.location)
      Toast.show({
        text: 'Location updated!',
        duration: 1000
  
      })
    }
  }

render() {

    const closeRow = (rowMap, rowKey) => {

      if (rowMap[rowKey]) {
          rowMap[rowKey].closeRow();
      }
    };

    const onRowDidOpen = rowKey => {
    
    };

    const deleteRow = async (rowMap, rowKey, id) => {
      closeRow(rowMap, rowKey);
      const newData = [...this.state.userConfigs];
      const prevIndex = this.state.userConfigs.findIndex(item => item.key === rowKey);
      newData.splice(prevIndex, 1);
      this.setState({userConfigs: newData})
      await setUserConfig(JSON.stringify({userConfigs:newData}));
      this.deleteFromDb(id);
    };

    const renderItem = data => (
      <TouchableHighlight
          onPress={() => this.handleEditButton(data.item)}
          style={styles.rowFront}
          underlayColor={'#AAA'}>
          <View>
              <Text style={{color:"#007bff"}}>{data.item.location}</Text>
          </View>
      </TouchableHighlight>
    );


    const renderHiddenItem = (data, rowMap) => (
      <View style={styles.rowBack}>
          <TouchableOpacity
              style={[styles.backRightBtn, styles.backRightBtnLeft]}
              onPress={() => closeRow(rowMap, data.item.location, data.item.id)}
          >
              <Text style={styles.backTextWhite}>Close</Text>
          </TouchableOpacity>
          <TouchableOpacity
              style={[styles.backRightBtn, styles.backRightBtnRight]}
              onPress={() => deleteRow(rowMap, data.item.location, data.item.id)}
          >
              <Text style={styles.backTextWhite}>Delete</Text>
          </TouchableOpacity>
      </View>
      );


    return (
      <Content padder>

        {this.state.textStatus === "Update"  &&
          <Header >
            <Right>
              <Button onPress={this.handleBackToSaveButton} transparent>
                <Text>Back To Save</Text>
              </Button>
            </Right>
          </Header>
       }
      
        <Card transparent>
          <CardItem>
             <Body >

            <Item floatingLabel>
              <Icon type="Octicons"  name='location' />
              <Label>Location</Label>
              <Input onChangeText={this.handleChangeText}  value={ this.state.location } onSubmitEditing={this.handleSubmitEditing}  ></Input>
            </Item>
      
             </Body>
          </CardItem>
          <CardItem>
            <Body>
            <View  style={{flex: 1,   alignSelf: 'stretch'}} >
              <Slider
              step={1}
              style={{flex: 1}}
              maximumValue={3}
              value={this.state.timeInterval}
              onValueChange={value => this.setState({ timeInterval: value })}
            />
             <Text>
                Location: {this.state.location}
             </Text>
             <Text>
                Time Interval: {this.state.timeInterval}
             </Text>
             </View>

            </Body>
          </CardItem>  
        </Card>

        <Button block dark onPress={this.saveUserConfig}>
            <Text>{this.state.textStatus}  Configs</Text>
        </Button>
        <Text>{'\n'}</Text>
        <Button iconLeft  primary >
          <Icon type="Octicons" name='location' />
          <Text>Locations</Text>
        </Button>
        <SwipeListView
          data={this.state.userConfigs}
          renderItem={renderItem}
          renderHiddenItem={renderHiddenItem}
          leftOpenValue={75}
          rightOpenValue={-150}
          previewRowKey={'0'}
          previewOpenValue={-40}
          previewOpenDelay={3000}
          onRowDidOpen={onRowDidOpen}
          keyExtractor = { (item, index) => item.location }
        />



      </Content>
    );
   }
}


const styles = StyleSheet.create({
  container: {
      backgroundColor: 'white',
      flex: 1,
  },
  backTextWhite: {
      color: '#FFF',
  },
  rowFront: {
      alignItems: 'center',
      backgroundColor: '#FFF',
      borderBottomColor: '#007bff',
      borderBottomWidth: 1,
      justifyContent: 'center',
      height: 50,
  },
  rowBack: {
      alignItems: 'center',
      backgroundColor: '#DDD',
      flex: 1,
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingLeft: 15,
  },
  backRightBtn: {
      alignItems: 'center',
      bottom: 0,
      justifyContent: 'center',
      position: 'absolute',
      top: 0,
      width: 75,
  },
  backRightBtnLeft: {
      backgroundColor: 'black',
      right: 75,
  },
  backRightBtnRight: {
      backgroundColor: 'crimson',
      right: 0,
  },
  editBtn: {
    backgroundColor: 'crimson',
    right: 0
},
});