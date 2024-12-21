import grpc
import computation_pb2_grpc
from computation_pb2 import DataRange

channel = grpc.insecure_channel('localhost:4040')
remote = computation_pb2_grpc.ComputeSupportStub(channel)
generateValuesRequest = DataRange(lower=10, upper=100, count=5)
combineValuesList = []
generateValuesResponse = remote.GenerateValues(generateValuesRequest)
for msg in generateValuesResponse:
    print('%.3f' % (msg.value))
    combineValuesList.append(msg)
combineValuesRequest = iter(combineValuesList)
combineValuesResponse = remote.CombineValues(combineValuesRequest)
print('Result = %.3f' % (combineValuesResponse.value)) 
