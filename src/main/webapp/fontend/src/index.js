import React from 'react';
import ReactDOM from 'react-dom';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';

import Home from './Home';

const routes = [
  { path: '/',
    exact: true,
    main: () => <Home/>
  },
  { path: '/about',
    main: () => <h2>About</h2>
  },
  { path: '/topics',
    main: () => <h2>Topics</h2>
  }
]

const Index = () => (
  <Router>
    <div>
      <ul>
        <li><Link to="/">首页</Link></li>
        <li><Link to="/about">小组</Link></li>
        <li><Link to="/topics">日志</Link></li>
        <li><Link to="/topics">专辑</Link></li>
        <li><Link to="/topics">会员</Link></li>
        <li><Link to="/topics">主页</Link></li>
      </ul>
        {routes.map((route, index) => (
            <Route
                key={index}
                path={route.path}
                exact={route.exact}
                component={route.main}
            />
        ))}
    </div>
  </Router>
)


ReactDOM.render((
  <Index/>
), document.getElementById('root'))
