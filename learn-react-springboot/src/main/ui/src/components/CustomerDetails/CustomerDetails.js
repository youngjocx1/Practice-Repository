import React, { useEffect } from "react";
import { Button, Alert } from "@trussworks/react-uswds";
import { useState } from "react";
import "./CustomerDetails.scss";
import VerticalTextGroup from "../VerticalTextGroup/VerticalTextGroup";
import {
  MedicationColumnMappings,
  ConditionColumnMappings,
  ObservationColumnMappings,
  EncounterColumnMappings,
} from "./ColumnMappings";
import {
  getCustomerMedicationsById,
  getCustomerConditionsId,
  getCustomerEncountersById,
  getCustomerObservationsById,
} from "../../api/back-end";
import CategoryTable from "../CategoryTable/CategoryTable";

export const CustomerDetails = ({
  linkedCustomers,
  customerIndex,
  setScreen,
  setCustomerIndex,
}) => {
  const [showMedication, setShowMedication] = useState(true);
  const [showObservations, setShowObservations] = useState(false);
  const [showConditions, setShowConditions] = useState(false);
  const [showEncounters, setShowEncounters] = useState(false);

  const [currentTab, setCurrentTab] = useState("Medication");

  const [medData, setMedDat] = useState([]);
  const [obsData, setObsDat] = useState([]);
  const [condData, setCondDat] = useState([]);
  const [encData, setEncDat] = useState([]);
  const [loading, setLoading] = useState(false);

  const resetTabs = () => {
    setShowMedication(false);
    setShowObservations(false);
    setShowConditions(false);
    setShowEncounters(false);
  };

  const backHandler = (e) => {
    e.preventDefault();
    setScreen("customerTable");
  };

  const nextPage = () => {
    resetTabs();
    setShowMedication(true);
    setCustomerIndex(customerIndex + 1);
  };

  const previousPage = () => {
    resetTabs();
    setShowMedication(true);
    setCustomerIndex(customerIndex - 1);
  };

  const getCurrentPageCustomer = () => {
    return linkedCustomers[customerIndex];
  };

  const tabMenuClick = (e, buttonName) => {
    e.preventDefault();

    if (currentTab !== buttonName) {
      resetTabs();
      setCurrentTab(buttonName);

      switch (buttonName) {
        case "Medication":
          setShowMedication(true);
          break;
        case "Observation":
          setShowObservations(true);
          break;
        case "Condition":
          setShowConditions(true);
          break;
        default:
          setShowEncounters(true);
      }
    }
  };

  const fetchData = async () => {
    const medDataReq = await getCustomerMedicationsById(
      getCurrentPageCustomer()["patientId"]
    );

    const obsDataReq = await getCustomerObservationsById(
      getCurrentPageCustomer()["patientId"]
    );

    const condDataReq = await getCustomerConditionsId(
      getCurrentPageCustomer()["patientId"]
    );

    const encDataReq = await getCustomerEncountersById(
      getCurrentPageCustomer()["patientId"]
    );

    setMedDat(medDataReq.data);
    setObsDat(obsDataReq.data);
    setEncDat(encDataReq.data);
    setCondDat(condDataReq.data);

    setLoading(false);
  };

  useEffect(() => {
    setLoading(true);
    fetchData();
  }, [customerIndex]);

  return (
    <div className="customer-detail-container grid-col">
      <div className="grid-row margin-bottom-2">
        <div className="display-flex flex-column flex-1 flex-align-self-center">
          <Button
            onClick={backHandler}
            outline
            className="margin-bottom-1 no-wrap"
          >
            Back to Results
          </Button>
          {customerIndex > 0 && (
            <Button
              data-testid="previous-button"
              onClick={previousPage}
              className="no-wrap"
            >
              {" <"} Previous record
            </Button>
          )}
        </div>

        <div className="display-flex flex-column flex-1 flex-align-center" />

        <div className="display-flex flex-column flex-3 flex-align-center">
          {linkedCustomers.length > 1 && (
            <Alert data-testid="alert" type="warning">
              THIS SSN HAS MULTIPLE RECORDS ASSOCIATED <br />
              Please review all associated records, view the details of each,
              and navigate to the details page tagged as the PRIME RECORD.
            </Alert>
          )}
        </div>

        <div className="display-flex flex-column flex-1 flex-align-center" />

        <div className="display-flex flex-column flex-1 flex-align-self-center">
          {customerIndex < linkedCustomers.length - 1 && (
            <Button
              data-testid="next-button"
              onClick={nextPage}
              className="no-wrap"
            >
              Next Record {" >"}
            </Button>
          )}
        </div>
      </div>

      <div className="grid-row padding-1">
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"DOB"}
            value={getCurrentPageCustomer()["birthDate"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"SSN"}
            value={"XXX-XX-" + getCurrentPageCustomer()["ssn"].substring(7, 11)}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Gender"}
            value={getCurrentPageCustomer()["gender"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Race"}
            value={getCurrentPageCustomer()["race"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Ethnicity"}
            value={getCurrentPageCustomer()["ethnicity"]}
          />
        </div>
        <div className="grid-col flex-1"></div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Patient ID"}
            value={getCurrentPageCustomer()["patientId"]}
          />
        </div>
      </div>

      <div className="grid-row padding-1">
        <div className="grid-col flex-2">
          <VerticalTextGroup
            label={"Address"}
            value={getCurrentPageCustomer()["address"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"County"}
            value={getCurrentPageCustomer()["county"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Marital Status"}
            value={getCurrentPageCustomer()["maritalStatus"]}
          />
        </div>
        <div className="grid-col flex-1">
          <VerticalTextGroup
            label={"Maiden Name"}
            value={getCurrentPageCustomer()["maidenName"]}
          />
        </div>
        <div className="grid-col flex-2"></div>
      </div>

      <div className="grid-row padding-1">
        <div className="grid-col flex-2">
          <VerticalTextGroup
            label={"Drivers License"}
            value={getCurrentPageCustomer()["drivers"]}
          />
        </div>
        <div className="grid-col flex-2">
          <VerticalTextGroup
            label={"Passport"}
            value={getCurrentPageCustomer()["passport"]}
          />
        </div>

        <div className="grid-col flex-3"></div>
      </div>

      <div className="grid-row margin-top-3">
        <div className="display-flex flex-column flex-1">
          <Button
            onClick={(event) => {
              tabMenuClick(event, "Medication");
            }}
            data-testid="medications"
            outline={!showMedication}
            size="big"
            type="button"
          >
            Medications
          </Button>
        </div>
        <div className="display-flex flex-column flex-1">
          <Button
            onClick={(event) => {
              tabMenuClick(event, "Observation");
            }}
            data-testid="observations"
            outline={!showObservations}
            size="big"
            type="button"
          >
            Observations
          </Button>
        </div>
        <div className="display-flex flex-column flex-1">
          <Button
            onClick={(event) => {
              tabMenuClick(event, "Condition");
            }}
            data-testid="conditions"
            outline={!showConditions}
            size="big"
            type="button"
          >
            Conditions
          </Button>
        </div>
        <div className="display-flex flex-column flex-1">
          <Button
            data-testid="encounters"
            onClick={(event) => {
              tabMenuClick(event, "Encounter");
            }}
            outline={!showEncounters}
            size="big"
            type="button"
          >
            Encounters
          </Button>
        </div>
      </div>

      {showMedication && (
        <CategoryTable
          title={"Customer Medications"}
          mappings={MedicationColumnMappings}
          loading={loading}
          data={medData}
        />
      )}
      {showConditions && (
        <CategoryTable
          loading={loading}
          data={condData}
          title={"Customer Conditions"}
          mappings={ConditionColumnMappings}
        />
      )}
      {showEncounters && (
        <CategoryTable
          loading={loading}
          data={encData}
          title={"Customer Encounters"}
          mappings={EncounterColumnMappings}
        />
      )}
      {showObservations && (
        <CategoryTable
          loading={loading}
          data={obsData}
          title={"Customer Observations"}
          mappings={ObservationColumnMappings}
        />
      )}
    </div>
  );
};

export default CustomerDetails;
