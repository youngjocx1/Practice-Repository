import React from "react";
import { useState } from "react";
import { Button } from "@trussworks/react-uswds";

const Search = ({ setScreen, setSearchCriteria }) => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [address, setAddress] = useState("");
  const [dob, setDob] = useState("");
  const [social, setSocial] = useState("");
  const [license, setLicense] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");

  const submitHandler = async (event) => {
    if (event) {
      event.preventDefault();
    }

    setSearchCriteria({
      address: address === "" ? null : address,
      drivers: license === "" ? null : license,
      firstName: firstName === "" ? null : firstName,
      lastName: lastName === "" ? null : lastName,
      ssn: social === "" ? null : social,
      city: city === "" ? null : city,
      dob: dob === "" ? null : dob,
      state: state === "" ? null : state,
    });

    setScreen("customerTable");
  };

  const clearHandler = async (event) => {
    event.preventDefault();

    setFirstName("");
    setLastName("");
    setAddress("");
    setDob("");
    setSocial("");
    setLicense("");
    setCity("");
    setState("");

    setSearchCriteria({
      address: null,
      drivers: null,
      firstName: null,
      lastName: null,
      ssn: null,
      city: null,
      zipCode: null,
      dob: null,
      state: null,
    });

    setScreen("customerTable");
  };

  return (
    <main className="grid-col flex-1 padding-0 outline-1px">
      <h3 className="grid-row padding-1">Search</h3>
      <form className="search-box grid-row" onSubmit={submitHandler}>
        <div className="usa-form-group grid-row">
          <label className="usa-label" htmlFor="search-input-last-name">
            Last Name
          </label>
          <input
            type="text"
            className="usa-input"
            data-testid="search-input-last-name"
            id="search-input-last-name"
            name="search-input-name"
            placeholder="Last Name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        <div className="usa-form-group first-name-row grid-row">
          <div className="display-flex flex-column">
            <label className="usa-label" htmlFor="search-input-first-name">
              First Name
            </label>
            <input
              type="text"
              className="usa-input"
              data-testid="search-input-first-name"
              id="search-input-first-name"
              name="search-input-name"
              placeholder="First Name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </div>
        </div>

        <div className="usa-form-group grid-row">
          <label className="usa-label" htmlFor="search-input-date-of-birth">
            Date of Birth
          </label>
          <input
            className="usa-input"
            id="search-input-date-of-birth"
            data-testid="search-input-date-of-birth"
            name="search-input-date-of-birth"
            placeholder="MM/DD/YYYY"
            pattern="^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{4}$"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
          />
        </div>

        <div className="usa-form-group grid-row">
          <label className="usa-label" htmlFor="search-input-social">
            Social Security Number (SSN)
          </label>
          <input
            className="usa-input"
            id="search-input-social"
            data-testid="search-input-social"
            name="search-input-social"
            placeholder="XXX-XX-XXXX"
            pattern="^[0-9]{3}-[0-9]{2}-[0-9]{4}$"
            value={social}
            onChange={(e) => setSocial(e.target.value)}
          />
        </div>

        <div className="usa-form-group grid-row">
          <label className="usa-label" htmlFor="search-input-license">
            Driver's License (DL)
          </label>
          <input
            className="usa-input"
            id="search-input-license"
            data-testid="search-input-license"
            name="search-input-license"
            placeholder="D-XXX-XXX-XXX"
            value={license}
            onChange={(e) => setLicense(e.target.value)}
          />
        </div>

        <div className="usa-form-group grid-row">
          <label className="usa-label" htmlFor="search-input-address">
            Address
          </label>
          <input
            className="usa-input"
            id="search-input-address"
            data-testid="search-input-address"
            name="search-input-address"
            placeholder="Address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
          <input
            className="usa-input"
            id="search-input-address-city"
            name="search-input-address-city"
            data-testid="search-input-address-city"
            placeholder="City"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
        </div>

        <div className="usa-form-group grid-row">
          <div className="usa-combo-box state display-flex flex-column flex-2 margin-right-1">
            <label className="usa-label" htmlFor="search-input-state">
              State
            </label>
            <select
              onChange={(e) => setState(e.target.value)}
              className="usa-select"
              id="search-input-state"
              name="state"
              data-testid="search-input-state"
              value={state}
            >
              <option value>- Select -</option>
              <option value="Alabama">AL - Alabama</option>
              <option value="Alaska">AK - Alaska</option>
              <option value="American Samoa">AS - American Samoa</option>
              <option value="Arizona">AZ - Arizona</option>
              <option value="Arkansas">AR - Arkansas</option>
              <option value="California">CA - California</option>
              <option value="Colorado">CO - Colorado</option>
              <option value="Connecticut">CT - Connecticut</option>
              <option value="Delaware">DE - Delaware</option>
              <option value="District of Columbia">
                DC - District of Columbia
              </option>
              <option value="Florida">FL - Florida</option>
              <option value="Georgia">GA - Georgia</option>
              <option value="Guam">GU - Guam</option>
              <option value="Hawaii">HI - Hawaii</option>
              <option value="Idaho">ID - Idaho</option>
              <option value="Illinois">IL - Illinois</option>
              <option value="Indiana">IN - Indiana</option>
              <option value="Iowa">IA - Iowa</option>
              <option value="KKansasS">KS - Kansas</option>
              <option value="Kentucky">KY - Kentucky</option>
              <option value="Louisiana">LA - Louisiana</option>
              <option value="Maine">ME - Maine</option>
              <option value="Maryland">MD - Maryland</option>
              <option value="Massachusetts">MA - Massachusetts</option>
              <option value="Michigan">MI - Michigan</option>
              <option value="Minnesota">MN - Minnesota</option>
              <option value="Mississippi">MS - Mississippi</option>
              <option value="Missouri">MO - Missouri</option>
              <option value="Montana">MT - Montana</option>
              <option value="Nebraska">NE - Nebraska</option>
              <option value="Nevada">NV - Nevada</option>
              <option value="New Hampshire">NH - New Hampshire</option>
              <option value="New Jersey">NJ - New Jersey</option>
              <option value="New Mexico">NM - New Mexico</option>
              <option value="New York">NY - New York</option>
              <option value="North Carolina">NC - North Carolina</option>
              <option value="North Dakota">ND - North Dakota</option>
              <option value="Northern Mariana Islands">
                MP - Northern Mariana Islands
              </option>
              <option value="Ohio">OH - Ohio</option>
              <option value="Oklahoma">OK - Oklahoma</option>
              <option value="Oregon">OR - Oregon</option>
              <option value="Pennsylvania">PA - Pennsylvania</option>
              <option value="Puerto Rico">PR - Puerto Rico</option>
              <option value="Rhode Island">RI - Rhode Island</option>
              <option value="South Carolina">SC - South Carolina</option>
              <option value="South Dakota">SD - South Dakota</option>
              <option value="Tennessee">TN - Tennessee</option>
              <option value="Texas">TX - Texas</option>
              <option value="Utah">UT - Utah</option>
              <option value="Vermont">VT - Vermont</option>
              <option value="Virgin Islands">VI - Virgin Islands</option>
              <option value="Virginia">VA - Virginia</option>
              <option value="Washington">WA - Washington</option>
              <option value="West Virginia">WV - West Virginia</option>
              <option value="Wisconsin">WI - Wisconsin</option>
              <option value="Wyoming">WY - Wyoming</option>
            </select>
          </div>
        </div>

        <div className="usa-form-group grid-row">
          <Button data-testid="submit" type="submit">
            Search
          </Button>
          <Button data-testid="clear" onClick={clearHandler} outline>
            Clear
          </Button>
        </div>
      </form>
    </main>
  );
};

export default Search;
