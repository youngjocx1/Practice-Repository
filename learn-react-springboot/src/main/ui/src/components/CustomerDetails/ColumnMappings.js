export const MedicationColumnMappings = [
  {
    name: "Description",
    width: "400px",
    selector: (row) => row.description,
  },
  {
    name: "Start Date",
    width: "200px",
    sortable: true,
    sortFunction: (a, b) => {
      return new Date(a.startDate) - new Date(b.startDate);
    },
    selector: (row) => row.startDate,
  },
  {
    name: "Stop Date",
    width: "200px",
    selector: (row) => row.stopDate,
  },
  {
    name: "Reason Description",
    width: "400px",
    selector: (row) => row.reasonDescription,
  },
  {
    name: "Reason Code",
    selector: (row) => row.reasonCode,
  },
  {
    name: "Dispenses",
    selector: (row) => row.dispenses,
  },
  {
    name: "Base Cost",
    selector: (row) => row.baseCost,
  },
  {
    name: "Payer Coverage",
    selector: (row) => row.payerCoverage,
  },
  {
    name: "Total Cost",
    selector: (row) => row.totalCost,
  },
];

export const ObservationColumnMappings = [
  {
    name: "Description",
    width: "400px",
    selector: (row) => row.description,
  },
  {
    name: "Date/Time",
    width: "200px",
    sortable: true,
    sortFunction: (a, b) => {
      return new Date(a.date) - new Date(b.date);
    },
    selector: (row) => row.date,
  },
  {
    name: "Code",
    selector: (row) => row.code,
  },
  {
    name: "Value",
    selector: (row) => row.value,
  },
  {
    name: "Units",
    selector: (row) => row.units,
  },
  {
    name: "Unit Type",
    selector: (row) => row.unitType,
  },
];

export const ConditionColumnMappings = [
  {
    name: "Description",
    width: "400px",
    selector: (row) => row.description,
  },
  {
    name: "Begin Date",
    width: "200px",
    sortable: true,
    sortFunction: (a, b) => {
      return new Date(a.beginDate) - new Date(b.beginDate);
    },
    selector: (row) => row.beginDate,
  },
  {
    name: "End Date",
    width: "200px",
    selector: (row) => row.endDate,
  },
  {
    name: "Condition Code",
    selector: (row) => row.conditionCode,
  },
];

export const EncounterColumnMappings = [
  {
    name: "Encounter Description",
    width: "400px",
    selector: (row) => row.description,
  },
  {
    name: "Reason Description",
    width: "400px",
    selector: (row) => row.reasonDescription,
  },
  {
    name: "Reason Code",
    selector: (row) => row.reasonCode,
  },
  {
    name: "Start",
    width: "200px",
    sortable: true,
    sortFunction: (a, b) => {
      return new Date(a.start) - new Date(b.start);
    },
    selector: (row) => row.start,
  },
  {
    name: "Stop",
    width: "200px",
    selector: (row) => row.stop,
  },
  {
    name: "Encounter Code",
    selector: (row) => row.encounterCode,
  },
  {
    name: "Encounter Class",
    width: "400px",
    selector: (row) => row.encounterClass,
  },
  {
    name: "Base Encounter Cost",
    selector: (row) => row.baseEncounterCost,
  },
  {
    name: "Total Claim Cost",
    selector: (row) => row.totalClaimCost,
  },
  {
    name: "Payer Coverage",
    selector: (row) => row.payerCoverage,
  },
  {
    name: "Reason Code",
    selector: (row) => row.reasonCode,
  },
];
