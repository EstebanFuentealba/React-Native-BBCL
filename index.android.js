/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var ToastAndroid = require('ToastAndroid');
var BBCLLayout = require('./components/BBCLLayout');
var {
  AppRegistry,
  Dimensions,
  View,
  NativeModules,
} = React;

var BBCL = NativeModules.BBCL;
class Reactbbcl extends React.Component {
  constructor(props){
    super(props);
    let json = eval("(" + this.props.jsonBBCL + ")");
    this.state = {
      json: json
    };
  }
  componentDidMount() {
    BBCL.setDefaultColor();
  }
  render() {
    return (
      <BBCLLayout json={this.state.json}/>
    )
  }
}
AppRegistry.registerComponent('reactbbcl', () => Reactbbcl);
