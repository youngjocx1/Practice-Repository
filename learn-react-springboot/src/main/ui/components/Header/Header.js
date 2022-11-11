import uscisLogo from "../../assets/images/USCIS_logo_English.svg.png";

import React from "react";

const Header = () => {
  return (
    <div className="header">
      <header className="usa-header">
        <div className="header-logo">
          <img src={uscisLogo} alt="USCIS Logo" />
        </div>
        <div className="header-text uscis-dark-gray">
          <h1>Worldwide Pharmacy Chain (WPC) System</h1>
        </div>
        <div className="sign-out">
          <button type="sign-out" className="usa-button sign-out">
            Sign Out
          </button>
        </div>
      </header>
    </div>
  );
};

export default Header;
