import React, { Component } from 'react';
import Header from '../Components/Header';
import { Button } from 'antd';
import './index.less';

class Home extends Component {
    render() {
      return (
        <div className="home">
          <Header/>
          <Button type="primary">Busssstton</Button>
        </div>
      );
    }
  }

  export default Home;