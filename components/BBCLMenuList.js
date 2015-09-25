'use strict';

let React               = require('react-native');

let {
  ListView,
  View,
  StyleSheet,
  Text,
  PixelRatio,
  TouchableHighlight
} = React;

var styles = StyleSheet.create({
  listContainer: {
    flex: 1,
  },
  list: {
    backgroundColor: '#1C2236',
    color: '#FFF'
  },
  sectionHeader: {
    padding: 5,
  },
  group: {
    backgroundColor: 'white',
  },
  sectionHeaderTitle: {
    fontWeight: '500',
    fontSize: 11,
    color: "#B4B4B4"
  },
  row: {
    backgroundColor: '#1C2236',
    justifyContent: 'center',
    paddingHorizontal: 15,
    paddingTop: 10,
  },
  rowContainer: {
    paddingLeft: 40,
  },
  rowTitleText: {
    fontSize: 17,
    fontWeight: '500',
    writingDirection: 'rtl',
    color: '#f3f3f3',
    lineHeight: 45,
  },
  separator: {
    height: 1 / PixelRatio.get(),
    backgroundColor: '#191E2F',
    marginLeft: 15,
  },
});

class BBCLMenuList extends React.Component {
  constructor(props) {
    super(props);
    let ds = new ListView.DataSource({
      rowHasChanged: (r1, r2) => r1 !== r2,
      sectionHeaderHasChanged: (h1, h2) => h1 !== h2,
    });
    this.state = {
      dataSource: ds.cloneWithRowsAndSections({
        secciones: ['a','b','c'],
      })
    };
  }
  renderRow(example: any, i: number) {
    return (
      <View key={i}>
        <TouchableHighlight onPress={() => this.onPressRow(example)}>
          <View style={styles.row}>
            <View style={styles.rowContainer}>
              <Text style={styles.rowTitleText}>
                Prueba
              </Text>
            </View>
          </View>
        </TouchableHighlight>
        <View style={styles.separator} />
      </View>
    );
  }
  _renderSectionHeader(data: any, section: string) {
   return (
     <View style={styles.sectionHeader}>
       <Text style={styles.sectionHeaderTitle}>
         SECCIONES
       </Text>
     </View>
   );
 }
  render() {
    return (
      <View style={styles.listContainer}>
        <ListView
          style={styles.list}
          dataSource={this.state.dataSource}
          renderRow={this.renderRow.bind(this)}
          renderSectionHeader={this._renderSectionHeader}
          keyboardShouldPersistTaps={true}
          automaticallyAdjustContentInsets={false}
          keyboardDismissMode="on-drag"
        />
      </View>
    );
  }
}
module.exports = BBCLMenuList;
