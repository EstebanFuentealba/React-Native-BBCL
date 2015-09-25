/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var BBCLLayout = require('./components/BBCLLayout');
var {
  AppRegistry,
  Dimensions,
  View,
} = React;
class Reactbbcl extends React.Component {
  constructor(props){
    super(props);
  }
  render() {
    return (
      <BBCLLayout />
    )
  }
}
AppRegistry.registerComponent('reactbbcl', () => Reactbbcl);
