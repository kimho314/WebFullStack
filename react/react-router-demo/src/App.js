import React from 'react';
import { Link, Route, Switch } from 'react-router-dom';
import Category from './components/Category';
import Home from './components/Home';
import Products from './components/Products';


function App() {
  return (
    <div>
      <nav className="navbar navbar-light">
        <ul className="nav navbar-nav">
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/category">Category</Link>
          </li>
          <li>
            <Link to="/products">Products</Link>
          </li>
        </ul>
      </nav>

      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/category" component={Category} />
        <Route path="/products" component={Products} />
        <Route path="/:id">
          <p>This text will render for any route other than those defined above</p>
        </Route>
      </Switch>
    </div>
  );
}

export default App;
