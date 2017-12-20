import React from 'react';
import ReactDOM from 'react-dom';
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom';

import Home from './Home';
import routes from './routes.js';
import '../src/common/base.less';

const getComponent = (text)=>{
  switch(text){
    case '首页':
      return ()=> <Home/>
    default:
      return ()=><h2>Others</h2>
  }
}

const Index = () => (
  <Router>
    <div>
        {routes.map((route, index) => (
            <Route
                key={index}
                path={route.path}
                exact={route.exact}
                component={getComponent(route.text)}
            />
        ))}
    </div>
  </Router>
)


ReactDOM.render((
  <Index/>
), document.getElementById('root'))
