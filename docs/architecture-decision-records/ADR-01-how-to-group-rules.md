# ADR 01: Grouping rules and corrections

## Options to explore

- Use rule flows combined with BPMN
  - Requires writing them in DRL rather than DMN
- Handle these via custom Java orchestration
  - still with DMN
- BPMN models
  - Custom Item handlers
  - Custom Scripts
- Separately deployed rules services (via REST/Queue)
