import React from 'react';
import './App.css';
import {Navigation} from "./components/Navigation/Navigation";
import {BrowserRouter} from "react-router-dom";
import {NavigationBar} from "./components/Navigation/NavigationBar";

function App() {
  return (
      <>
          <BrowserRouter>
              <NavigationBar />
              <Navigation />
          </BrowserRouter>
      </>
  )
}

export default App;
