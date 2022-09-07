package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.3/#5.10.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.3/#5.10.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryReadResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=665");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=667");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=666");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15275");

    private final ResponseHeader responseHeader;

    private final HistoryReadResult[] results;

    private final DiagnosticInfo[] diagnosticInfos;

    public HistoryReadResponse(ResponseHeader responseHeader, HistoryReadResult[] results,
                               DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.results = results;
        this.diagnosticInfos = diagnosticInfos;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public HistoryReadResult[] getResults() {
        return results;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 667),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("Results", LocalizedText.NULL_VALUE, new NodeId(0, 638), 1, null, UInteger.valueOf(0), false),
                new StructureField("DiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryReadResponse> {
        @Override
        public Class<HistoryReadResponse> getType() {
            return HistoryReadResponse.class;
        }

        @Override
        public HistoryReadResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            HistoryReadResult[] results = (HistoryReadResult[]) decoder.readStructArray("Results", HistoryReadResult.TYPE_ID);
            DiagnosticInfo[] diagnosticInfos = decoder.readDiagnosticInfoArray("DiagnosticInfos");
            return new HistoryReadResponse(responseHeader, results, diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryReadResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStructArray("Results", value.getResults(), HistoryReadResult.TYPE_ID);
            encoder.writeDiagnosticInfoArray("DiagnosticInfos", value.getDiagnosticInfos());
        }
    }
}
