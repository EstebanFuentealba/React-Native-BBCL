'use strict';

let React               = require('react-native');
let ToolbarAndroid      = require('ToolbarAndroid');
let DrawerLayoutAndroid = require('DrawerLayoutAndroid');
let BBCLMenuList            = require('./BBCLMenuList');
let DRAWER_WIDTH_LEFT = 56;

let {
  View,
  Dimensions,
  StyleSheet,
  Text,
} = React;


let styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  toolbar: {
    backgroundColor: '#004D93',
    height: 56,
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

class BBCLLayout extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "Prueba "
    }
  }
  _renderNavigationView() {
    return (
      <BBCLMenuList />
    )
  }
  render() {
    return (
      <DrawerLayoutAndroid
        drawerPosition={DrawerLayoutAndroid.positions.Left}
        drawerWidth={Dimensions.get('window').width - DRAWER_WIDTH_LEFT}
        keyboardDismissMode="on-drag"
        ref={(drawer) => { this.drawer = drawer; }}
        renderNavigationView={this._renderNavigationView}>
        <View style={styles.container}>
          <ToolbarAndroid
            logo={require('image!ic_action_logo')}
            navIcon={require('image!ic_action_expand_dark')}
            onIconClicked={() => this.drawer.openDrawer()}
            style={styles.toolbar}
            titleColor="#b4b4b4"
            title="BBCL"
          />
        </View>
      </DrawerLayoutAndroid>
    );
  }
}
module.exports = BBCLLayout;
