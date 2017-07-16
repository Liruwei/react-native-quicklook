/* @flow */

import React, {
    Component,
    PropTypes,
} from 'react';
import {
    AppRegistry,
    Image,
    StyleSheet,
    Text,
    View,
    Dimensions,
    TouchableOpacity,
    NativeModules,
    Platform,
    requireNativeComponent
} from 'react-native';
var QuickLook = NativeModules.QuickLook;
var QuickLookIOS = requireNativeComponent('RCTQuickLook', null);

const SCREEN_WIDTH = Dimensions.get('window').width;
const SCREEN_HEIGHT = Dimensions.get('window').height;
const styles = StyleSheet.create({
    center:{
        alignItems:'center',
        justifyContent:'center',
    },
    fill:{
        flex:1,
    },
    absolute:{
        position: 'absolute',
    },
    fillAbsolute:{
        position: 'absolute',
        top:0,
        left:0,
        right:0,
        bottom:0,
    },
    fileIcon: {
        width: 100,
        height: 100,
    },
    fileName: {
        padding: 10,
        fontWeight: 'bold',
        fontSize: 15,
    }
});


export default class QuickLookView extends React.Component{

    static defaultProps = {
        fileUrl: null
    };
    static propTypes = {
        fuleUrl: PropTypes.string
    };

    constructor(props) {
        super(props);
        this.state = {};
    }

    componentWillMount() {}
    componentDidMount() {}
    componentWillUnmount(){}

    getName(fileUrl) {
        let fileName = null;
        let temp = fileUrl.split("/");;
        if (temp.length>0) {
            fileName = temp[temp.length-1];
        }
        return fileName;
    }

    getIcon(fileUrl) {
        let fileIcon = require('./sources/none.png');
        let fileName = this.getName(fileUrl);
        if (fileName) {
            let temp = fileName.split('.');
            if (temp.length > 0) {
                let type = temp[temp.length-1];
                if (type.indexOf('doc')!==-1) {
                    fileIcon = require('./sources/doc.png');
                } else if (type.indexOf('xls')!==-1) {
                    fileIcon = require('./sources/xls.png');
                } else if (type.indexOf('ppt')!==-1) {
                    fileIcon = require('./sources/ppt.png');
                } else if (type.indexOf('pdf')!==-1) {
                    fileIcon = require('./sources/pdf.png');
                } else if (type.indexOf('txt')!==-1) {
                    fileIcon = require('./sources/txt.png');
                }
            }
        }
        return fileIcon;
    }

    openFile(fileUrl) {
        QuickLook.openFile(fileUrl);
    }

    render() {
        let {style={},fileUrl=''} = this.props;
        if (Platform.OS=='android') {
            return  <View style={[styles.fill,styles.center,style]}>
                <TouchableOpacity onPress={()=>{this.openFile(fileUrl)}}>
                    <View style={styles.center}>
                        <Image style={styles.fileIcon} source={this.getIcon(fileUrl)}/>
                        <Text style={styles.fileName}>{this.getName(fileUrl)}</Text>
                    </View>
                </TouchableOpacity>
            </View>
        } else {
            return <QuickLookIOS {...this.props}/>
        }
    }
}
