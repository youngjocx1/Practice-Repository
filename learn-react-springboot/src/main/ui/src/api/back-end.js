import axios from "axios";
import config from "../config";

export const getCustomersPaginated = async (
  searchCriteria,
  page = 1,
  perPage = config.app.defaultPageSize
) => {
  const url = `${config.services.backEnd.uri}/patients/paginated?pageNumber=${page}&maxPerPage=${perPage}`;

  return axios.post(url, searchCriteria);
};

export const getCustomerById = async (customerId) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}`;

  return axios.get(url);
};

export const getCustomerMedicationsById = async (customerId) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}/medications`;

  return axios.get(url);
};

export const getCustomerObservationsById = async (customerId) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}/observations`;

  return axios.get(url);
};

export const getCustomerConditionsId = async (customerId) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}/conditions`;

  return axios.get(url);
};

export const getCustomerEncountersById = async (customerId) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}/encounters`;

  return axios.get(url);
};

export const getCustomerRelatedBySSN = async (customerId, ssn) => {
  const url = `${config.services.backEnd.uri}/patients/${customerId}/related/${ssn}`;

  return axios.get(url);
};
