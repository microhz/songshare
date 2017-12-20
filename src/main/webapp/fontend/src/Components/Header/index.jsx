import React, { Component } from 'react';
import {
    Link
} from 'react-router-dom';

import './index.less';
import routes from '../../routes.js';

class Header extends Component {
    render() {
        return (
            <header className="home">
                {routes.map((route,index) => (
                    <Link to={route.path} key={index}>
                    {route.text}
                    </Link>
                ))}
            </header>
        );
    }
}

export default Header;