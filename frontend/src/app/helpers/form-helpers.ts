// Utility function to remove null/undefined values from an object
export function removeNullValues(obj: any): any {
  const cleanedData: any = {};

  Object.keys(obj).forEach(key => {
    const value = obj[key];

    if (value !== null && value !== undefined) {
      // For arrays, remove null values recursively
      if (Array.isArray(value)) {
        cleanedData[key] = value.filter(item => item !== null && item !== undefined);
      } else {
        cleanedData[key] = value;
      }
    }
  });

  return cleanedData;
}
