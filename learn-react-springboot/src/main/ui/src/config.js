export const config = {
  app: {
    /*
    host:
      getConfigValue('REACT_APP_EASEY_ECMPS_UI_HOST', 'easey-dev.app.cloud.gov'),
    */
    path: process.env.REACT_APP_USCIS_ESIS_UI_PATH || "/",
    env: process.env.REACT_APP_USCIS_ESIS_UI_ENV || "local-dev",
    title: process.env.REACT_APP_USCIS_ESIS_UI_TITLE || "USCIS-ESIS",
    defaultPageSize: process.env.REACT_APP_USCIS_ESIS_DEFAULT_PAGE_SIZE || 25,
  },
  services: {
    backEnd: {
      uri: process.env.REACT_APP_USCIS_ESIS_BACK_END_URI || "",
    },
  },
};

export default config;
